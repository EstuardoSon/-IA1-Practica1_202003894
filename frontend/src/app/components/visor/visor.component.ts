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

  constructor(private backendService: BackendService) {}
  ngOnInit(): void {}

  onUpload(event: any) {
    event.preventDefault();

    let formData = new FormData();

    if (this.file) {
      formData.append("file", this.file);
      this.backendService.postURL("/scan", formData).subscribe(
        (res) => {
          console.log(res);
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
  }
}
