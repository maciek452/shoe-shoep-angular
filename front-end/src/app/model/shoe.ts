import {ShoesCollection} from './shoesCollection';
import { Brand } from './brand';

export class Shoe {
  id: number;
  model: string;
  brand: Brand;
  size: number;
  shoesCollections: ShoesCollection[];
}
