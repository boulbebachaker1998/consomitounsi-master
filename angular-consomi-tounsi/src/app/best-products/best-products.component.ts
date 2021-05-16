import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/models/product';
import { CategoryProduit } from '../models/categoryProduit';
import { ProductService } from '../product.service';
import { ActivatedRoute, Router } from '@angular/router';
@Component({
  selector: 'app-best-products',
  templateUrl: './best-products.component.html',
  styleUrls: ['./best-products.component.css']
})
export class BestProductsComponent implements OnInit {
  product: Product=new Product(0,new CategoryProduit(0,""),0,"",0,new Date(),0,0) ;
  listProduct: Product[];
  constructor(private service:ProductService,private router : Router) { }

  ngOnInit(): void {this.service.getBestProduct().subscribe(
    (data:Product[])=>this.listProduct=data );
  }
  public buyproduit(produit:Product ){
    this.router.navigateByUrl('/buy', { state: produit});
  }
}
