import React from 'react';
import {Car, Setup, Track} from '../types';
import {Button, Card} from 'react-bootstrap';

interface SelectedSetupProps {
    car: Car;
    track: Track;
    setup: Setup;
    onRemove: () => void;
}

const SelectedSetup: React.FC<SelectedSetupProps> = ({car, track, setup, onRemove}) => {
    return (
        <Card style={{width: '18rem', minWidth: '18rem'}} className="me-3">
            <Card.Body>
                <Card.Title>{car.name}</Card.Title>
                <Card.Subtitle className="mb-2 text-muted">{track.name}</Card.Subtitle>
                <Card.Text>
                    <strong>Setup:</strong> {setup.name}
                </Card.Text>
                <Button variant="danger" onClick={onRemove}>Remove</Button>
            </Card.Body>
        </Card>
    );
};

export default SelectedSetup;
