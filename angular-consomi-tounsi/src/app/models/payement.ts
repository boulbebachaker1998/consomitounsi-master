import { Product } from "./product";
import { User } from "./user";

export  class Payement{
    constructor(
    public id:number,       
    public createDate:Date,
    public  quantity:number,
    public  amount:number,
    public status:string,
    public  payementType:string,
    public  referenceExterne:string,
    public  user:User,
    public  product:Product
     ){}
}
