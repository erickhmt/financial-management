import { Column, Entity, PrimaryGeneratedColumn } from 'typeorm';

@Entity()
export class Pagamento {
  @PrimaryGeneratedColumn()
  id: number;

  @Column({ type: 'bigint' })
  valor: number;

  @Column({ type: 'date' })
  data: Date;

  @Column()
  status: string;

  // Despesa / Receita / Transferência (d / r / t)
  @Column({ type: 'enum', enum: ['d', 'r', 't'] })
  tipo: string;
  descricao: string;
}
