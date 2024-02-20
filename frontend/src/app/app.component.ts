import { Component } from "@angular/core";
import { RouterOutlet } from "@angular/router";
import { VisorComponent } from "./components/visor/visor.component";
import { EtiquetasComponent } from "./components/etiquetas/etiquetas.component";

@Component({
  selector: "app-root",
  standalone: true,
  templateUrl: "./app.component.html",
  styleUrl: "./app.component.css",
  imports: [RouterOutlet, EtiquetasComponent, VisorComponent],
})
export class AppComponent {
  title = "frontend";
}
