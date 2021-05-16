import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/models/product';
import { CategoryProduit } from '../models/categoryProduit';
import { ProductService } from '../product.service';
@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.css']
})
export class UpdateComponent implements OnInit {
  product: Product;
  imgaeFile : File;
  constructor(private service:ProductService) { }

  ngOnInit(): void {
    this.product=history.state;
  }
  public modifyProduct(){
    let resp=this.service.doModification(this.product);
    resp.subscribe((data)=>console.log(data))}

    public fileUploadProduct(){
      let resp=this.service.fileUpload(this.imgaeFile,this.product.idProduit);
    resp.subscribe((data)=>console.log(data))
    }

    public onFileChange(event){
      this.imgaeFile = event.target.files[0];
    }
}
