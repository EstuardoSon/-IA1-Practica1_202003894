import { Component } from '@angular/core';
import { BackendService } from '../../services/backend.service';

@Component({
  selector: 'app-etiquetas',
  standalone: true,
  imports: [],
  templateUrl: './etiquetas.component.html',
  styleUrl: './etiquetas.component.css'
})
export class EtiquetasComponent {
  constructor(private backendService: BackendService) {
  }

  tags = this.backendService.tags;
  cont = 0; 
}
