import React, {useEffect, useRef, useState} from 'react';
import {Button, Card, Col, Container, Form, Row, Spinner} from 'react-bootstrap';

const AiChat = () => {
    const [prompt, setPrompt] = useState('');
    const [chatHistory, setChatHistory] = useState('');
    const [isBusy, setIsBusy] = useState(false);
    const textareaRef = useRef<HTMLTextAreaElement>(null);

    useEffect(() => {
        if (textareaRef.current) {
            textareaRef.current.scrollTop = textareaRef.current.scrollHeight;
        }
    }, [chatHistory]);

    const handleSend = async () => {
        if (!prompt) return;

        setIsBusy(true);
        setChatHistory(prev => prev + `You: ${prompt}\n`);
        setPrompt('');

        const startTime = Date.now();

        try {
            const response = await fetch('/api/ai/chat', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({prompt}),
            });

            if (!response.body) return;

            const endTime = Date.now();
            const duration = endTime - startTime;

            const reader = response.body.getReader();
            const decoder = new TextDecoder();

            setChatHistory(prev => prev + 'AI: ');
            while (true) {
                const {done, value} = await reader.read();
                if (done) {
                    break;
                }
                const chunk = decoder.decode(value, {stream: true});
                setChatHistory(prev => prev + chunk);
            }
            setChatHistory(prev => prev + `\n[AI response took: ${duration} ms]\n`);
        } catch (error) {
            console.error('Error sending chat message:', error);
        } finally {
            setIsBusy(false);
        }
    };

    const handleClearHistory = () => {
        setChatHistory('');
    };

    return (
        <Container>
            <Row>
                <Col>
                    <Card>
                        <Card.Body>
                            <Card.Title>AI Chat</Card.Title>
                            <Form.Control
                                as="textarea"
                                ref={textareaRef}
                                rows={20}
                                readOnly
                                value={chatHistory}
                                placeholder="AI response..."
                                className="mb-3"
                            />
                            <Form>
                                <Row>
                                    <Col>
                                        <Form.Control
                                            type="text"
                                            value={prompt}
                                            onChange={(e) => setPrompt(e.target.value)}
                                            onKeyPress={(e) => e.key === 'Enter' && handleSend()}
                                            placeholder="Enter your prompt"
                                            disabled={isBusy}
                                        />
                                    </Col>
                                    <Col xs="auto">
                                        <Button onClick={handleSend} disabled={isBusy} variant="primary">
                                            {isBusy ? <Spinner as="span" animation="border" size="sm" role="status" aria-hidden="true" /> : 'Send'}
                                        </Button>
                                    </Col>
                                    <Col xs="auto">
                                        <Button onClick={handleClearHistory} disabled={isBusy} variant="secondary">
                                            Clear History
                                        </Button>
                                    </Col>
                                </Row>
                            </Form>
                        </Card.Body>
                    </Card>
                </Col>
            </Row>
        </Container>
    );
};

export default AiChat;
