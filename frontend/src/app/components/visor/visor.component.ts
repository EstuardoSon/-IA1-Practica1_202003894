import { Component } from "@angular/core";
import { FormControl, ReactiveFormsModule } from "@angular/forms";
import { BackendService } from "../../services/backend.service";

@Component({
  selector: "app-visor",
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: "./visor.component.html",
  styleUrl: "./visor.component.css",
})
export class VisorComponent {
  file: Blob | null = null;
  url = "";
  validacion = "";

  estilo = {
    filter: `blur(0px)`
  }

  constructor(private backendService: BackendService) {}
  ngOnInit(): void {}

  onUpload(event: any) {
    event.preventDefault();

    let formData = new FormData();

    if (this.file) {
      formData.append("file", this.file);
      this.backendService.postURL("/scan", formData).subscribe(
        (res:any) => {
          console.log(res);
          this.backendService.setTags(res.tags);
          this.backendService.setCont(res.rostros);

          if(res.tags[0].porcentaje >= 40 || res.tags[3].porcentaje >= 50 || res.tags[4].porcentaje >= 59) {
            this.estilo = {
              filter: `blur(10px)`
            }
          }

          let sum = res.tags[0].porcentaje + res.tags[3].porcentaje + res.tags[4].porcentaje;
          if (sum >= 45) {
            this.validacion = "Imagen no apta para la institución";
          } else {
            this.validacion = "Imagen valida";
          }
        },
        (err) => {
          console.log(err);
        }
      );
    }
  }

  onFileChange(event: any) {
    this.url = URL.createObjectURL(event.target.files[0]);
    this.file = event.target.files[0];
    this.validacion = "";
    this.estilo = {
      filter: `blur(0px)`
    }
  }
}
