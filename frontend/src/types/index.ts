export interface Setup {
    id: number;
    name: string;
    data: any; // In a real scenario, this would be the setup data
}

export interface Track {
    id: number;
    name: string;
    setups: Setup[];
}

export interface Car {
    id: number;
    name: string;
    tracks: Track[];
}
