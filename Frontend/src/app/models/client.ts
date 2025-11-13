import { Offre } from "./offre";
import { Evenement } from "./evenement";
import { Reservation } from "./reservation";
import { Paiement } from "./paiement";
import { Avis } from "./avis";


export interface Client {
    id: number;
    nom: string;
    email: string;
    motDePasse: string;
    reservations: Reservation[];
    avis: Avis[];
    paiements: Paiement[];
    offres: Offre[];
    evenements: Evenement[];
  }