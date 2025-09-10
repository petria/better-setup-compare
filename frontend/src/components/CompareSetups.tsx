import React, {useEffect, useState} from 'react';
import {Car, Setup, Track} from '../types';
import {Form} from 'react-bootstrap';
import SetupComparator from './SetupComparator';
import {getCars, getSetups, getTracks} from '../services/api';

const CompareSetups: React.FC = () => {
    const [cars, setCars] = useState<Car[]>([]);
    const [tracks, setTracks] = useState<Track[]>([]);
    const [setups, setSetups] = useState<Setup[]>([]);

    const [selectedCar, setSelectedCar] = useState<Car | null>(null);
    const [selectedTrack, setSelectedTrack] = useState<Track | null>(null);
    const [selectedSetup, setSelectedSetup] = useState<Setup | null>(null);

    useEffect(() => {
        getCars().then(setCars);
    }, []);

    const handleCarChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
        const carName = event.target.value;
        const car = cars.find(c => c.name === carName) || null;
        setSelectedCar(car);
        setSelectedTrack(null);
        setSelectedSetup(null);
        if (car) {
            getTracks(car.id).then(setTracks);
        } else {
            setTracks([]);
        }
        setSetups([]);
    };

    const handleTrackChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
        const trackName = event.target.value;
        const track = tracks.find(t => t.name === trackName) || null;
        setSelectedTrack(track);
        setSelectedSetup(null);
        if (selectedCar && track) {
            getSetups(selectedCar.name, track.name).then(setSetups);
        } else {
            setSetups([]);
        }
    };

    const handleSetupChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
        const setupName = event.target.value;
        const setup = setups.find(s => s.name === setupName) || null;
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
                            <option key={car.name} value={car.name}>{car.name}</option>
                        ))}
                    </Form.Select>
                </Form.Group>

                <Form.Group className="mb-3">
                    <Form.Label>Track</Form.Label>
                    <Form.Select onChange={handleTrackChange} disabled={!selectedCar}>
                        <option>Select a track</option>
                        {tracks.map(track => (
                            <option key={track.name} value={track.name}>{track.name}</option>
                        ))}
                    </Form.Select>
                </Form.Group>

                <Form.Group className="mb-3">
                    <Form.Label>Setup</Form.Label>
                    <Form.Select onChange={handleSetupChange} disabled={!selectedTrack}>
                        <option>Select a setup</option>
                        {setups.map(setup => (
                            <option key={setup.name} value={setup.name}>{setup.name}</option>
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
