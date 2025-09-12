import React, {useEffect, useState} from 'react';
import {CarForSelection, SetupForCarSelection, TrackForCarSelection} from '../types';
import {Button, Form} from 'react-bootstrap';
import SetupComparator from './SetupComparator';
import {getCars, getSetups, getTracks} from '../services/api';

const CompareSetups: React.FC = () => {
    const [cars, setCars] = useState<CarForSelection[]>([]);
    const [tracks, setTracks] = useState<TrackForCarSelection[]>([]);
    const [setups, setSetups] = useState<SetupForCarSelection[]>([]);

    const [selectedCar, setSelectedCar] = useState<CarForSelection | null>(null);
    const [selectedTrack, setSelectedTrack] = useState<TrackForCarSelection | null>(null);
    const [selectedSetup, setSelectedSetup] = useState<SetupForCarSelection | null>(null);

    const [carFilter, setCarFilter] = useState('');
    const [trackFilter, setTrackFilter] = useState('');
    const [setupFilter, setSetupFilter] = useState('');

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
        setTrackFilter('');
        setSetupFilter('');
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
        setSetupFilter('');
    };

    const handleSetupChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
        const setupName = event.target.value;
        const setup = setups.find(s => s.setupIniFileName === setupName) || null;
        setSelectedSetup(setup);
    };

    const filteredCars = cars.filter(car => car.carFolderName.toLowerCase().includes(carFilter.toLowerCase()));
    const filteredTracks = tracks.filter(track => track.trackFolderName.toLowerCase().includes(trackFilter.toLowerCase()));
    const filteredSetups = setups.filter(setup => setup.setupIniFileName.toLowerCase().includes(setupFilter.toLowerCase()));

    return (
        <div>
            <h1>Compare Setups</h1>
            <Form>
                <Form.Group className="mb-3">
                    <Form.Label>Car</Form.Label>
                    <div className="d-flex">
                        <Form.Control
                            type="text"
                            placeholder="Filter cars"
                            value={carFilter}
                            onChange={e => setCarFilter(e.target.value)}
                        />
                        <Button variant="outline-secondary" onClick={() => setCarFilter('')}>Clear</Button>
                    </div>
                    <Form.Select onChange={handleCarChange} value={selectedCar?.carFolderName || ''}>
                        <option>Select a car</option>
                        {filteredCars.map(car => (
                            <option key={car.carFolderName} value={car.carFolderName}>{car.carFolderName}</option>
                        ))}
                    </Form.Select>
                </Form.Group>

                <Form.Group className="mb-3">
                    <Form.Label>Track</Form.Label>
                    <div className="d-flex">
                        <Form.Control
                            type="text"
                            placeholder="Filter tracks"
                            value={trackFilter}
                            onChange={e => setTrackFilter(e.target.value)}
                            disabled={!selectedCar}
                        />
                        <Button variant="outline-secondary" onClick={() => setTrackFilter('')} disabled={!selectedCar}>Clear</Button>
                    </div>
                    <Form.Select onChange={handleTrackChange} disabled={!selectedCar} value={selectedTrack?.trackFolderName || ''}>
                        <option>Select a track</option>
                        {filteredTracks.map(track => (
                            <option key={track.trackFolderName} value={track.trackFolderName}>{track.trackFolderName}</option>
                        ))}
                    </Form.Select>
                </Form.Group>

                <Form.Group className="mb-3">
                    <Form.Label>Setup</Form.Label>
                    <div className="d-flex">
                        <Form.Control
                            type="text"
                            placeholder="Filter setups"
                            value={setupFilter}
                            onChange={e => setSetupFilter(e.target.value)}
                            disabled={!selectedTrack}
                        />
                        <Button variant="outline-secondary" onClick={() => setSetupFilter('')} disabled={!selectedTrack}>Clear</Button>
                    </div>
                    <Form.Select onChange={handleSetupChange} disabled={!selectedTrack} value={selectedSetup?.setupIniFileName || ''}>
                        <option>Select a setup</option>
                        {filteredSetups.map(setup => (
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
