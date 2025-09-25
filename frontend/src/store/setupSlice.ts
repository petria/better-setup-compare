
import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { CarForSelection, SetupForCarSelection, TrackForCarSelection } from '../types';

interface SetupState {
    selectedCar: CarForSelection | null;
    selectedTrack: TrackForCarSelection | null;
    selectedSetup: SetupForCarSelection | null;
}

const initialState: SetupState = {
    selectedCar: null,
    selectedTrack: null,
    selectedSetup: null,
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
    },
});

export const { setSelectedCar, setSelectedTrack, setSelectedSetup } = setupSlice.actions;
export default setupSlice.reducer;
