import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/models/product';
import { ProductService } from '../product.service';
import { CategoryProduit } from '../models/categoryProduit';
import { ActivatedRoute, Router } from '@angular/router';
import { UploadFile } from '../models/uploadFile';
@Component({
  selector: 'app-adminProducts',
  templateUrl: './admin-products.component.html',
  styleUrls: ['./admin-products.component.css']
})
export class AdminProductsComponent implements OnInit {
  listProduct: Product[];
  product: Product=new Product(0,new CategoryProduit(0,""),0,"",0,new Date(),0,0) ;
  nom:string;

  constructor(private service:ProductService,private router : Router) { }

  public delteProduct(id:number){
    let resp= this.service.deleteProduct(id);
    resp.subscribe((data:Product[])=>this.listProduct=data);
   }
  public findProductByName(){
    let resp= this.service.getProductByname(this.nom);
    resp.subscribe((data:Product[])=>this.listProduct=data);
   }


   public updateproduit(produit:Product ){
     this.router.navigateByUrl('/update', { state: produit});
   }

  ngOnInit(): void {
    this.service.getProduct().subscribe(
      (data:Product[])=>this.listProduct=data );

  }

}