import React, {useState} from 'react';
import {CarForSelection, SetupForCarSelection, TrackForCarSelection} from '../types';
import SelectedSetup from './SelectedSetup';
import {Button} from 'react-bootstrap';

interface SetupComparatorProps {
    selectedCar: CarForSelection | null;
    selectedTrack: TrackForCarSelection | null;
    selectedSetup: SetupForCarSelection | null;
}

interface ComparisonItem {
    id: number;
    car: CarForSelection;
    track: TrackForCarSelection;
    setup: SetupForCarSelection;
}

const SetupComparator: React.FC<SetupComparatorProps> = ({selectedCar, selectedTrack, selectedSetup}) => {
    const [comparisonList, setComparisonList] = useState<ComparisonItem[]>([]);
    const [nextId, setNextId] = useState(0);

    const handleAddSetup = () => {
        if (selectedCar && selectedTrack && selectedSetup) {
            setComparisonList([...comparisonList, {
                id: nextId,
                car: selectedCar,
                track: selectedTrack,
                setup: selectedSetup
            }]);
            setNextId(nextId + 1);
        }
    };

    const handleRemoveSetup = (id: number) => {
        setComparisonList(comparisonList.filter(item => item.id !== id));
    };

    return (
        <div>
            <hr/>
            <h2>Setup Comparator</h2>
            <Button onClick={handleAddSetup} disabled={!selectedSetup} className="mb-3">Add Current Setup to
                Comparison</Button>
            <div style={{display: 'flex', flexDirection: 'row', overflowX: 'auto'}}>
                {comparisonList.map(item => (
                    <SelectedSetup
                        key={item.id}
                        car={item.car}
                        track={item.track}
                        setup={item.setup}
                        onRemove={() => handleRemoveSetup(item.id)}
                    />
                ))}
            </div>
        </div>
    );
};

export default SetupComparator;
