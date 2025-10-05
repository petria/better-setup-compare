import React from 'react';
import {Card} from 'react-bootstrap';
import {useAuth} from '../hooks/useAuth';

const ImportSetups: React.FC = () => {
    const auth = useAuth();

    return (
        <Card style={{width: '18rem'}}>
            <Card.Body>
                <Card.Title>Hello, {auth.user?.username}!</Card.Title>
                <Card.Text>
                    Import setups
                </Card.Text>
            </Card.Body>
        </Card>
    );
};

export default ImportSetups;