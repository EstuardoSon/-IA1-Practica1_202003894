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
  
  constructor(public backendService: BackendService) {
  }

  getColor(value: number) {
    if (value >= 100) {
      return 'progress-bar bg-danger';
    }else if (value >= 75) {
      return 'progress-bar bg-warning';
    }else if (value >= 50) {
      return 'progress-bar bg-success';
    }else if (value >= 25) {
      return 'progress-bar bg-info';
    }
    return 'progress-bar bg-info';
  }
}
