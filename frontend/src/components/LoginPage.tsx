import React, {useEffect, useState} from 'react';
import {useAuth} from '../hooks/useAuth';
import {useLocation, useNavigate} from 'react-router-dom';
import {Alert, Button, Container, Form} from 'react-bootstrap';

const LoginPage: React.FC = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState<string | null>(null);
    const auth = useAuth();
    const navigate = useNavigate();
    const location = useLocation();
    const from = location.state?.from?.pathname || "/";

    useEffect(() => {
        if (auth.isAuthenticated) {
            navigate(from, {replace: true});
        }
    }, [auth.isAuthenticated, navigate, from]);

    const handleSubmit = async (event: React.FormEvent) => {
        event.preventDefault();
        setError(null);
        try {
            await auth.login(username, password);
        } catch (error) {
            if (error instanceof TypeError && error.message === 'Failed to fetch') {
                setError('Backend not reached');
            } else {
                setError('Invalid username or password');
            }
            console.error('Failed to login:', error);
        }
    };

    return (
        <Container>
            <Form onSubmit={handleSubmit}>
                <h2 className="mb-3">Login</h2>
                {error && <Alert variant="danger">{error}</Alert>}
                <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Label>Username</Form.Label>
                    <Form.Control type="text" placeholder="Enter username" value={username}
                                  onChange={e => setUsername(e.target.value)}/>
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicPassword">
                    <Form.Label>Password</Form.Label>
                    <Form.Control type="password" placeholder="Password" value={password}
                                  onChange={e => setPassword(e.target.value)}/>
                </Form.Group>
                <Button variant="primary" type="submit">
                    Login
                </Button>
            </Form>
        </Container>
    );
};

export default LoginPage;