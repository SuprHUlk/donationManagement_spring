import { Component } from '@angular/core';
import { NgoService } from 'src/app/service/ngo.service';

@Component({
  selector: 'app-ngo',
  templateUrl: './ngo.component.html',
  styleUrls: ['./ngo.component.css']
})
export class NgoComponent {

  ngos: any;

  constructor(private ngoService: NgoService) { }

  ngOnInit() {
    this.ngoService.getAllNgos().subscribe(
      (Response: any)=> {
        this.ngos=Response;
        console.log(Response);
      },
      (Error: any)=> {
        console.log(Error);
      }
    )
  }

  onClick() {

  }
}
