import {CarForSelection, OllamaServerConfig, SetupForCarSelection, TrackForCarSelection} from '../types';

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

export const getOllamaServerConfigs = async (): Promise<OllamaServerConfig[]> => {
    const response = await fetch('/api/ai/getOllamaServerConfigs');
    return response.json();
};

export const getChatInitMessage = async (): Promise<string> => {
    const response = await fetch('/api/ai/getChatInitMessage');
    return response.text();
};

export const sendChatCommand = async (command: string): Promise<string> => {
    const response = await fetch('/api/ai/command', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({command}),
    });
    return response.text();
};

export const sendChatMessage = async (prompt: string, serverUrl: string): Promise<string> => {
    const response = await fetch('/api/ai/chat', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({prompt, serverUrl}),
    });
    return response.text();
};
