export interface SetupForCarSelection {
    id: number;
    carFolderName: string;
    trackFolderName: string;
    setupIniFileName: string;
}

export interface TrackForCarSelection {
    id: number;
    trackName: string;
    trackFolderName: string;
    iniFileCount: number;
}

export interface CarForSelection {
    id: number;
    carTracksWithSetup: number;
    carName: string;
    carFolderName: string;
}

export interface Setup {
    id: number;
    name: string;
    data: any; // In a real scenario, this would be the setup data
}

export interface OllamaServerConfig {
  id: number;
  serverUrl: string;
  serverName: string;
  status: string;
  availableModelNames: string[];
}