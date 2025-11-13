import { Client } from "./client";
export interface Offre {
    id: number;
    titre: string;
    description: string;
    prixReduit: number;
    images: string[];  
    clients?: Client[];
  }
  