import { Client } from "./client";
export interface Evenement {
    id: number;
    nom: string;
    lieu: string;
    date_e: string;  // Date de l'événement (en format ISO)
    images: string[];  
    participants?: Client[];
  }
  