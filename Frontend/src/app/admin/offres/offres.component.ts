import { Component } from '@angular/core';
import { AdminService } from '../../services/Admin.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpHeaders,HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-offres',
  imports: [FormsModule,CommonModule],
  templateUrl: './offres.component.html',
  styleUrl: './offres.component.css'
})
export class OffresComponent {
   offres: any[] = [];
  newOffre: any = { 
    id: 0, 
    titre: '', 
    description: '', 
    prixReduit: 0, 
    images: [] 
  };
  editingOffre: any = null;
  editImageInput: string = '';
  imageInput: string = '';

  constructor(private adminService: AdminService, private http: HttpClient,private router: Router) {}

  ngOnInit() {
    this.loadOffres();
  }

  loadOffres() {
    this.adminService.getAllOffres().subscribe(data => {
      this.offres = data;
    });
  }

  addOffre() {
    const url = 'http://localhost:8082/api/admins/offres';  // URL de ton API Spring Boot

    const offreData = {
      titre: this.newOffre.titre,
      description: this.newOffre.description,
      prixReduit: this.newOffre.prixReduit,
      images: this.newOffre.images
    };

    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    this.http.post(url, offreData, { headers: headers })
      .subscribe((response: any) => {
        console.log('Offre ajoutée avec succès', response);
        this.loadOffres();
        this.resetNewOffre();
      }, (error: any) => {
        console.error('Erreur lors de l\'ajout de l\'offre', error);
      });
  }

  addImage(): void {
    if (this.imageInput) {
      const imageUrl = `http://localhost:8082/images/${this.imageInput}`;
      this.newOffre.images.push(imageUrl);
      this.imageInput = '';  
    }
  }

  deleteOffre(id: number) {
    this.adminService.deleteOffre(id).subscribe(() => {
      this.loadOffres();
    });
  }

  editOffre(offre: any) {
    this.editingOffre = { ...offre, images: [...offre.images] };
    this.editImageInput = '';
  }

  updateOffre() {
    this.adminService.updateOffre(this.editingOffre.id, this.editingOffre).subscribe({
      next: () => {
        this.loadOffres();
        this.editingOffre = null;
      },
      error: err => console.error('Erreur mise à jour offre', err)
    });
  }

  addImageToEditing() {
    if (this.editImageInput) {
      const imageUrl = `http://localhost:8082/images/${this.editImageInput}`;
      this.editingOffre.images.push(imageUrl);
      this.editImageInput = '';
    }
  }

  removeImage(index: number) {
    if (this.editingOffre?.images) {
      this.editingOffre.images.splice(index, 1);
    }
  }

  resetNewOffre() {
    this.newOffre = {
      id: 0,
      titre: '',
      description: '',
      prixReduit: 0,
      images: []
    };
  }
   goToDashboard() {
    this.router.navigate(['/admin']);  // Modifie '/dashboard' selon ton chemin de route
  }
}