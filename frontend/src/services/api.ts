import {CarForSelection, SetupForCarSelection, TrackForCarSelection} from '../types';

export const getHello = async (): Promise<string> => {
    const response = await fetch('/api/hello');
    const data = await response.text();
    return data;
};

export const getCars = async (): Promise<CarForSelection[]> => {
    const response = await fetch('/api/cars');
    return response.json();
};

export const getTracks = async (carFolderName: string): Promise<TrackForCarSelection[]> => {
    const response = await fetch(`/api/cars/${carFolderName}/tracks`);
    return response.json();
};

export const getSetups = async (carFolderName: string, trackFolderName: string): Promise<SetupForCarSelection[]> => {
    const response = await fetch(`/api/cars/${carFolderName}/tracks/${trackFolderName}/setups`);
    return response.json();
};