import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {HomeComponent} from './home/home.component';
import {Header} from './components/header/header';

@Component({
  selector: 'app-root',
  standalone: true,          // Make it a standalone component
  imports: [RouterOutlet, HomeComponent, Header],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class AppComponent {
  protected readonly title = signal('mychat');
}
