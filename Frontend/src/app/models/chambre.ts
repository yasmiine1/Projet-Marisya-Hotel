// chambre.model.ts
export interface Chambre {
    numero: number;
    type: string;
    prix: number;
    desc: string;
    capacite: number;
    disponibilite: boolean;
    images: string[];  
  }
  