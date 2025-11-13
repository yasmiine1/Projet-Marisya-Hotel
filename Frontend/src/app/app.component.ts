import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { MenuComponent } from './components/menu/menu.component';
import { AuthService } from './services/auth.service';
import { FooterComponent } from './components/footer/footer.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet,MenuComponent,FooterComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'project_Marisya';
  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    this.authService.restoreSession().subscribe({
      next: () => console.log('Session restaurée'),
      error: () => console.log('Aucune session active')
    });
  }
}
