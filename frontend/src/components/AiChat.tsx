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

            const responseData = await response.text();

            const endTime = Date.now();
            const duration = endTime - startTime;

            setChatHistory(prev => prev + `[AI response took: ${duration} ms]\n`);

            setChatHistory(prev => prev + `AI: ${responseData}\n`);

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
                            <Form onSubmit={(e) => { e.preventDefault(); handleSend(); }}>
                                <Row>
                                    <Col>
                                        <Form.Control
                                            type="text"
                                            value={prompt}
                                            onChange={(e) => setPrompt(e.target.value)}
                                            onKeyPress={(e) => {
                                                if (e.key === 'Enter') {
                                                    e.preventDefault();
                                                    handleSend();
                                                }
                                            }}
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
