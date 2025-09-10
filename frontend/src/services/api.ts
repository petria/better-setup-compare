import {Car, Setup, Track} from '../types';

export const getHello = async (): Promise<string> => {
    const response = await fetch('/api/hello');
    const data = await response.text();
    return data;
};

export const getCars = async (): Promise<Car[]> => {
    const response = await fetch('/api/cars');
    return response.json();
};

export const getTracks = async (carId: number): Promise<Track[]> => {
    const response = await fetch(`/api/cars/${carId}/tracks`);
    return response.json();
};

export const getSetups = async (carName: string, trackName: string): Promise<Setup[]> => {
    const response = await fetch(`/api/cars/${carName}/tracks/${trackName}/setups`);
    return response.json();
};