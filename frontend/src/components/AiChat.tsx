import React, {useEffect, useRef, useState} from 'react';

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
            const response = await fetch('/api/ai/stream/chat', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({prompt}),
            });

            if (!response.body) return;

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
            const endTime = Date.now();
            const duration = endTime - startTime;
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
        <div>
            <textarea
                ref={textareaRef}
                rows={20}
                cols={80}
                readOnly
                value={chatHistory}
                placeholder="AI response..."
            />
            <div>
                <input
                    type="text"
                    value={prompt}
                    onChange={(e) => setPrompt(e.target.value)}
                    onKeyPress={(e) => e.key === 'Enter' && handleSend()}
                    placeholder="Enter your prompt"
                    disabled={isBusy}
                />
                <button onClick={handleSend} disabled={isBusy}>Send</button>
                <button onClick={handleClearHistory} disabled={isBusy}>Clear History</button>
                {isBusy && <div>Waiting for response...</div>}
            </div>
        </div>
    );
};




export default AiChat;
