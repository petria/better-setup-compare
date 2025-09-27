
import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { CarForSelection, SetupForCarSelection, TrackForCarSelection } from '../types';

interface ComparisonItem {
    id: number;
    car: CarForSelection;
    track: TrackForCarSelection;
    setup: SetupForCarSelection;
}

interface SetupState {
    selectedCar: CarForSelection | null;
    selectedTrack: TrackForCarSelection | null;
    selectedSetup: SetupForCarSelection | null;
    comparisonList: ComparisonItem[];
    nextId: number;
}

const initialState: SetupState = {
    selectedCar: null,
    selectedTrack: null,
    selectedSetup: null,
    comparisonList: [],
    nextId: 0,
};

const setupSlice = createSlice({
    name: 'setup',
    initialState,
    reducers: {
        setSelectedCar(state, action: PayloadAction<CarForSelection | null>) {
            state.selectedCar = action.payload;
            state.selectedTrack = null;
            state.selectedSetup = null;
        },
        setSelectedTrack(state, action: PayloadAction<TrackForCarSelection | null>) {
            state.selectedTrack = action.payload;
            state.selectedSetup = null;
        },
        setSelectedSetup(state, action: PayloadAction<SetupForCarSelection | null>) {
            state.selectedSetup = action.payload;
        },
        addSetupToComparison(state, action: PayloadAction<{ car: CarForSelection, track: TrackForCarSelection, setup: SetupForCarSelection }>) {
            state.comparisonList.push({ ...action.payload, id: state.nextId });
            state.nextId++;
        },
        removeSetupFromComparison(state, action: PayloadAction<number>) {
            state.comparisonList = state.comparisonList.filter(item => item.id !== action.payload);
        },
    },
});

export const {
    setSelectedCar,
    setSelectedTrack,
    setSelectedSetup,
    addSetupToComparison,
    removeSetupFromComparison
} = setupSlice.actions;
export default setupSlice.reducer;
