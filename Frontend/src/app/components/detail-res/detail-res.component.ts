import { Component } from '@angular/core';
import { Chambre } from '../../models/chambre';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { ChambreService } from '../../services/chambre.service';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-detail-res',
  imports: [CommonModule],
  templateUrl: './detail-res.component.html',
  styleUrl: './detail-res.component.css'
})
export class DetailResComponent {
  chambre: any;
  dateDebut: string = '';
  dateFin: string = '';
  imageIndex: number = 0;

  constructor(
    private route: ActivatedRoute,
    private chambreService: ChambreService,
    private router: Router,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.params['id'];
    this.dateDebut = this.route.snapshot.queryParams['dateDebut'];
    this.dateFin = this.route.snapshot.queryParams['dateFin'];

    this.chambreService.getChambreById(id).subscribe(data => {
      this.chambre = data;
    });
  }

  prevSlide() {
    if (this.chambre && this.chambre.images.length > 0) {
      this.imageIndex = (this.imageIndex - 1 + this.chambre.images.length) % this.chambre.images.length;
    }
  }

  nextSlide() {
    if (this.chambre && this.chambre.images.length > 0) {
      this.imageIndex = (this.imageIndex + 1) % this.chambre.images.length;
    }
  }

  reserver() {
    if (this.authService.isLoggedIn()) {
      this.router.navigate(['/reserver2', this.chambre.numero], {
        queryParams: {
          dateDebut: this.dateDebut,
          dateFin: this.dateFin
        }
      });
    } else {
      // Sauvegarder temporairement les infos dans le localStorage
      localStorage.setItem('redirectAfterLogin', JSON.stringify({
        route: '/reserver2/' + this.chambre.numero,
        queryParams: {
          dateDebut: this.dateDebut,
          dateFin: this.dateFin
        }
      }));
      this.router.navigate(['/login']);
    }
  }
  
  
  retour() {
    this.router.navigate(['/reserver'], {
      queryParams: {
        dateDebut: this.dateDebut,
        dateFin: this.dateFin
      }
    });
  }
  
}


