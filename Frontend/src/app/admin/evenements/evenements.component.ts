import { Component } from '@angular/core';
import { AdminService } from '../../services/Admin.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Evenement } from '../../models/evenement';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-evenements',
  imports: [FormsModule,CommonModule],
  templateUrl: './evenements.component.html',
  styleUrl: './evenements.component.css'
})
export class EvenementsComponent {
evenements: Evenement[] = [];
  newEvenement: Evenement = {
    id: 0,
    nom: '',
    lieu: '',
    date_e: '',
    images: []
  };
  editingEvenement: any  = null;
  editImageInput: string = '';
  imageInput: string = '';

  constructor(private adminService: AdminService, private http: HttpClient,private router: Router) {}

  ngOnInit() {
    this.loadEvenements();
  }

  loadEvenements() {
    this.adminService.getAllEvenements().subscribe(data => {
      this.evenements = data;
    });
  }

  addEvenement() {
    const url = 'http://localhost:8082/api/admins/evenements';  // URL de ton API Spring Boot

    const evenementData = {
      nom: this.newEvenement.nom,
      lieu: this.newEvenement.lieu,
      date_e: this.newEvenement.date_e,
      images: this.newEvenement.images
    };

    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    this.http.post(url, evenementData, { headers: headers }).subscribe((response: any) => {
      console.log('Événement ajouté avec succès', response);
      this.loadEvenements();
      this.resetNewEvenement();
    }, (error: any) => {
      console.error('Erreur lors de l\'ajout de l\'événement', error);
    });
  }

  addImage() {
    if (this.imageInput) {
      const imageUrl = `http://localhost:8082/images/${this.imageInput}`;
      this.newEvenement.images.push(imageUrl);
      this.imageInput = '';
    }
  }

  deleteEvenement(id: number) {
    this.adminService.deleteEvenement(id).subscribe(() => {
      this.loadEvenements();
    });
  }

  editEvenement(evenement: Evenement) {
    this.editingEvenement = { ...evenement, images: [...evenement.images] };
    this.editImageInput = '';
  }

  updateEvenement() {
    if (this.editingEvenement) {
      this.adminService.updateEvenement(this.editingEvenement.id, this.editingEvenement).subscribe(() => {
        this.loadEvenements();
        this.editingEvenement = null;
      }, error => {
        console.error('Erreur mise à jour événement', error);
      });
    }
  }

  addImageToEditing() {
    if (this.editImageInput) {
      const imageUrl = `http://localhost:8082/images/${this.editImageInput}`;
      this.editingEvenement?.images.push(imageUrl);
      this.editImageInput = '';
    }
  }

  removeImage(index: number) {
    if (this.editingEvenement?.images) {
      this.editingEvenement.images.splice(index, 1);
    }
  }
 goToDashboard() {
    this.router.navigate(['/admin']);  // Modifie '/dashboard' selon ton chemin de route
  }
  resetNewEvenement() {
    this.newEvenement = {
      id: 0,
      nom: '',
      lieu: '',
      date_e: '',
      images: []
    };
  }

}
