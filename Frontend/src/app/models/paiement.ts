import { Client } from "./client";
import { Reservation } from "./reservation";

export interface Paiement {
    id?: number;
    montant: number;
    date: string; // Date au format ISO ou chaîne de caractères
    moyen: string;
    client?: Client; // Un paiement est associé à un client
    reservation?: Reservation; // Un paiement peut être lié à une réservation
  }
  