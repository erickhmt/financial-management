import {
  IsCurrency,
  IsDate,
  IsEnum,
  IsNotEmpty,
  IsString,
} from 'class-validator';

export class CreatePagamentoDto {
  @IsCurrency()
  valor: number;

  @IsDate()
  data: Date;

  @IsNotEmpty()
  status: string;

  // Despesa / Receita / Transferência (d / r / t)
  @IsString()
  @IsEnum(['d', 'r', 't'])
  tipo: string;

  @IsString()
  descricao: string;
}
