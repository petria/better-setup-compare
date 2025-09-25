import React, {useEffect, useState} from 'react';
import {CarForSelection, SetupForCarSelection, TrackForCarSelection} from '../types';
import {Button, Form} from 'react-bootstrap';
import SetupComparator from './SetupComparator';
import {getCars, getSetups, getTracks} from '../services/api';
import {useDispatch, useSelector} from 'react-redux';
import {RootState} from '../store';
import {setSelectedCar, setSelectedSetup, setSelectedTrack} from '../store/setupSlice';

const CompareSetups: React.FC = () => {
    const dispatch = useDispatch();
    const {selectedCar, selectedTrack, selectedSetup} = useSelector((state: RootState) => state.setup);

    const [cars, setCars] = useState<CarForSelection[]>([]);
    const [tracks, setTracks] = useState<TrackForCarSelection[]>([]);
    const [setups, setSetups] = useState<SetupForCarSelection[]>([]);

    const [carFilter, setCarFilter] = useState('');
    const [trackFilter, setTrackFilter] = useState('');
    const [setupFilter, setSetupFilter] = useState('');

    useEffect(() => {
        getCars().then(setCars);
    }, []);

    useEffect(() => {
        if (selectedCar) {
            getTracks(selectedCar.carFolderName).then(setTracks);
        } else {
            setTracks([]);
        }
        setSetups([]);
    }, [selectedCar]);

    useEffect(() => {
        if (selectedCar && selectedTrack) {
            getSetups(selectedCar.carFolderName, selectedTrack.trackFolderName).then(setSetups);
        } else {
            setSetups([]);
        }
    }, [selectedCar, selectedTrack]);

    const handleCarChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
        const carFolderName = event.target.value;
        const car = cars.find(c => c.carFolderName === carFolderName) || null;
        dispatch(setSelectedCar(car));
        setTrackFilter('');
        setSetupFilter('');
    };

    const handleTrackChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
        const trackName = event.target.value;
        const track = tracks.find(t => t.trackFolderName === trackName) || null;
        dispatch(setSelectedTrack(track));
        setSetupFilter('');
    };

    const handleSetupChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
        const setupName = event.target.value;
        const setup = setups.find(s => s.setupIniFileName === setupName) || null;
        dispatch(setSelectedSetup(setup));
    };

    const filteredCars = cars.filter(car => car.carFolderName.toLowerCase().includes(carFilter.toLowerCase()));
    const filteredTracks = tracks.filter(track => track.trackFolderName.toLowerCase().includes(trackFilter.toLowerCase()));
    const filteredSetups = setups.filter(setup => setup.setupIniFileName.toLowerCase().includes(setupFilter.toLowerCase()));

    return (
        <div>
            <h1>Setup selector</h1>
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
                            <option key={car.carFolderName}
                                    value={car.carFolderName}>{car.carFolderName} [{car.carTracksWithSetup}]</option>
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
                        <Button variant="outline-secondary" onClick={() => setTrackFilter('')}
                                disabled={!selectedCar}>Clear</Button>
                    </div>
                    <Form.Select onChange={handleTrackChange} disabled={!selectedCar}
                                 value={selectedTrack?.trackFolderName || ''}>
                        <option>Select a track</option>
                        {filteredTracks.map(track => (
                            <option key={track.trackFolderName}
                                    value={track.trackFolderName}>{track.trackFolderName}</option>
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
                        <Button variant="outline-secondary" onClick={() => setSetupFilter('')}
                                disabled={!selectedTrack}>Clear</Button>
                    </div>
                    <Form.Select onChange={handleSetupChange} disabled={!selectedTrack}
                                 value={selectedSetup?.setupIniFileName || ''}>
                        <option>Select a setup</option>
                        {filteredSetups.map(setup => (
                            <option key={setup.setupIniFileName}
                                    value={setup.setupIniFileName}>{setup.setupIniFileName}</option>
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
