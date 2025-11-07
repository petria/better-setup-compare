import React, {useState} from 'react';
import {useDispatch, useSelector} from 'react-redux';
import SelectedSetup from './SelectedSetup';
import {Button} from 'react-bootstrap';
import {RootState} from '../store';
import {addSetupToComparison, removeSetupFromComparison} from '../store/setupSlice';
import CompareResults from "./CompareResults";

const SetupComparator: React.FC = () => {
    const dispatch = useDispatch();
    const {selectedCar, selectedTrack, selectedSetup, comparisonList} = useSelector((state: RootState) => state.setup);
    const [showResults, setShowResults] = useState(false);

    const handleAddSetup = () => {
        if (selectedCar && selectedTrack && selectedSetup) {
            dispatch(addSetupToComparison({car: selectedCar, track: selectedTrack, setup: selectedSetup}));
        }
    };

    const handleRemoveSetup = (id: number) => {
        dispatch(removeSetupFromComparison(id));
    };

    const handleCompare = () => {
        setShowResults(true);
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
            <hr/>
            {comparisonList.length >= 2 && (
                <Button onClick={handleCompare} className="mb-3 ms-3">Compare</Button>
            )}
            {showResults && <CompareResults setups={comparisonList}/>}
        </div>
    );
};

export default SetupComparator;
