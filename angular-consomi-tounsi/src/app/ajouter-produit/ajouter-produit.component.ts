import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/models/product';
import { CategoryProduit } from '../models/categoryProduit';
import { ProductService } from '../product.service';
@Component({
  selector: 'app-ajouter-produit',
  templateUrl: './ajouter-produit.component.html',
  styleUrls: ['./ajouter-produit.component.css']
})
export class AjouterProduitComponent implements OnInit {
  product: Product=new Product(0,new CategoryProduit(0,""),0,"",0,new Date(),0,0) ;
  
  constructor(private service:ProductService)  { }

  ngOnInit(): void {
  }
  public addProduct(){
    this.product.dateCreation=null;
    let resp=this.service.doRegistration(this.product);
    resp.subscribe((data)=>console.log(data))}

}

