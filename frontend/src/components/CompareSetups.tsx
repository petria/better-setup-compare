import React, {useEffect, useState} from 'react';
import {CarForSelection, SetupForCarSelection, TrackForCarSelection} from '../types';
import {Form} from 'react-bootstrap';
import SetupComparator from './SetupComparator';
import {getCars, getSetups, getTracks} from '../services/api';

const CompareSetups: React.FC = () => {
    const [cars, setCars] = useState<CarForSelection[]>([]);
    const [tracks, setTracks] = useState<TrackForCarSelection[]>([]);
    const [setups, setSetups] = useState<SetupForCarSelection[]>([]);

    const [selectedCar, setSelectedCar] = useState<CarForSelection | null>(null);
    const [selectedTrack, setSelectedTrack] = useState<TrackForCarSelection | null>(null);
    const [selectedSetup, setSelectedSetup] = useState<SetupForCarSelection | null>(null);

    useEffect(() => {
        getCars().then(setCars);
    }, []);

    const handleCarChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
        const carFolderName = event.target.value;
        const car = cars.find(c => c.carFolderName === carFolderName) || null;
        setSelectedCar(car);
        setSelectedTrack(null);
        setSelectedSetup(null);
        if (car) {
            getTracks(car.carFolderName).then(setTracks);
        } else {
            setTracks([]);
        }
        setSetups([]);
    };

    const handleTrackChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
        const trackName = event.target.value;
        const track = tracks.find(t => t.trackFolderName === trackName) || null;
        setSelectedTrack(track);
        setSelectedSetup(null);
        if (selectedCar && track) {
            getSetups(selectedCar.carFolderName, track.trackFolderName).then(setSetups);
        } else {
            setSetups([]);
        }
    };

    const handleSetupChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
        const setupName = event.target.value;
        const setup = setups.find(s => s.setupIniFileName === setupName) || null;
        setSelectedSetup(setup);
    };

    return (
        <div>
            <h1>Compare Setups</h1>
            <Form>
                <Form.Group className="mb-3">
                    <Form.Label>Car</Form.Label>
                    <Form.Select onChange={handleCarChange}>
                        <option>Select a car</option>
                        {cars.map(car => (
                            <option key={car.carFolderName} value={car.carFolderName}>{car.carFolderName}</option>
                        ))}
                    </Form.Select>
                </Form.Group>

                <Form.Group className="mb-3">
                    <Form.Label>Track</Form.Label>
                    <Form.Select onChange={handleTrackChange} disabled={!selectedCar}>
                        <option>Select a track</option>
                        {tracks.map(track => (
                            <option key={track.trackFolderName} value={track.trackFolderName}>{track.trackFolderName}</option>
                        ))}
                    </Form.Select>
                </Form.Group>

                <Form.Group className="mb-3">
                    <Form.Label>Setup</Form.Label>
                    <Form.Select onChange={handleSetupChange} disabled={!selectedTrack}>
                        <option>Select a setup</option>
                        {setups.map(setup => (
                            <option key={setup.setupIniFileName} value={setup.setupIniFileName}>{setup.setupIniFileName}</option>
                        ))}
                    </Form.Select>
                </Form.Group>
            </Form>

            <SetupComparator
                selectedCar={selectedCar}
                selectedTrack={selectedTrack}
                selectedSetup={selectedSetup}
            />
        </div>
    );
};

export default CompareSetups;
