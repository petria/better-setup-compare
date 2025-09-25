
import { configureStore } from '@reduxjs/toolkit';
import setupReducer from './setupSlice';

export const store = configureStore({
    reducer: {
        setup: setupReducer,
    },
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
