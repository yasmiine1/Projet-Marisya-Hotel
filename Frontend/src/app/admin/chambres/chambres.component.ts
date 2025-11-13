import { Component } from '@angular/core';
import { AdminService } from '../../services/Admin.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Chambre } from '../../models/chambre';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-chambres',
  imports: [FormsModule,CommonModule],
  templateUrl: './chambres.component.html',
  styleUrl: './chambres.component.css'
})
export class ChambresComponent {
  chambres: any[] = [];
  newChambre: any = { 
    numero: 0,
    type: '', 
    prix: 0, 
    desc: '', 
    capacite: 0, 
    disponibilite: true, 
    images: [] 
  };
  editingChambre: any = null;
  editImageInput: string = '';
  

  imageInput: string = '';

  constructor(private adminService: AdminService, private http: HttpClient,private router: Router) {}

  ngOnInit() {
    this.loadChambres();
  }

  loadChambres() {
    this.adminService.getAllChambres().subscribe(data => {
      this.chambres = data;
    });
  }

  addChambre() {
    const url = 'http://localhost:8082/api/admins/chambres';  // URL de ton API Spring Boot

    // Données à envoyer, avec l'objet newChambre
    const chambreData = {
      type: this.newChambre.type,
      prix: this.newChambre.prix,
      capacite: this.newChambre.capacite,
      desc: this.newChambre.desc,
      images: this.newChambre.images,
      disponibilite: this.newChambre.disponibilite
    };

    // En-têtes pour indiquer que tu envoies du JSON
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    // Envoi des données au serveur en JSON
    this.http.post(url, chambreData, { headers: headers })
      .subscribe((response: any) => {
        console.log('Chambre ajoutée avec succès', response);
        this.loadChambres();  // Réactualise la liste des chambres
        this.resetNewChambre(); // Réinitialise les champs du formulaire
      }, (error: any) => {
        console.error('Erreur lors de l\'ajout de la chambre', error);
      });
  }
 goToDashboard() {
    this.router.navigate(['/admin']);  // Modifie '/dashboard' selon ton chemin de route
  }
  addImage(): void {
    if (this.imageInput) {
      const imageUrl = `http://localhost:8082/images/${this.imageInput}`;
      this.newChambre.images.push(imageUrl);
      this.imageInput = '';  // Vide le champ après ajout de l'image
    }
  }

  deleteChambre(numero: number) {
    this.adminService.deleteChambre(numero).subscribe(() => {
      this.loadChambres(); // Réactualise la liste des chambres après suppression
    });
  }
  editChambre(chambre: any) {
    this.editingChambre = { ...chambre, images: [...chambre.images] }; // copie profonde
    this.editImageInput = '';
  }
  

  updateChambre() {
    this.adminService.updateChambre(this.editingChambre.numero, this.editingChambre).subscribe({
      next: () => {
        this.loadChambres();
        this.editingChambre = null;
      },
      error: err => console.error('Erreur maj chambre', err)
    });
  }
  
  addImageToEditing() {
    if (this.editImageInput) {
      const imageUrl = `http://localhost:8082/images/${this.editImageInput}`;
      this.editingChambre.images.push(imageUrl);
      this.editImageInput = '';
    }
  }
  
  removeImage(index: number) {
    if (this.editingChambre?.images) {
      this.editingChambre.images.splice(index, 1);
    }
  }
  resetNewChambre() {
    this.newChambre = {
      numero: 0,
      type: '',
      prix: 0,
      desc: '',
      capacite: 0,
      disponibilite: true,
      images: []
    };
  }
  
}