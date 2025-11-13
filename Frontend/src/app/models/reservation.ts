import { Client } from "./client";
import { Paiement } from "./paiement";

import { DateReservation } from "./date-reservation";
export interface Reservation {
    id: number;
    statut: string;
    typeReservation: string;
    client: Client;
    paiement: Paiement;
    dateReservations: DateReservation[];
  }