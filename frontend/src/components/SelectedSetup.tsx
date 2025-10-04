import React from 'react';
import {CarForSelection, SetupForCarSelection, TrackForCarSelection} from '../types';
import {Button, Card} from 'react-bootstrap';

interface SelectedSetupProps {
    car: CarForSelection;
    track: TrackForCarSelection;
    setup: SetupForCarSelection;
    onRemove: () => void;
}

const SelectedSetup: React.FC<SelectedSetupProps> = ({car, track, setup, onRemove}) => {
    return (
        <Card style={{width: '18rem', minWidth: '18rem'}} className="me-3">
            <Card.Body>
                <Card.Title>{car.carFolderName}</Card.Title>
                <Card.Subtitle className="mb-2 text-muted">{track.trackFolderName}</Card.Subtitle>
                <Card.Text>
                    <strong>Id:</strong> {setup.id}<br/>
                    <strong>Setup:</strong> {setup.setupIniFileName}
                </Card.Text>
                <Button variant="danger" onClick={onRemove}>Remove</Button>
            </Card.Body>
        </Card>
    );
};

export default SelectedSetup;
