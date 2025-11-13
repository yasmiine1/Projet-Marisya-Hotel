import { Client } from "./client";

export interface Avis {
    id?: number;
    contenu: string;
    note: number;
    date_avis: string; // Date au format ISO ou chaîne de caractères
    etat: string;
    client?: Client; // Un avis est associé à un client
  }
  