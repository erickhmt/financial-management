import { Injectable } from '@nestjs/common';
import { CreatePagamentoDto } from './dto/create-pagamento.dto';
import { UpdatePagamentoDto } from './dto/update-pagamento.dto';
import { InjectRepository } from '@nestjs/typeorm';
import { Pagamento } from './entities/pagamento.entity';
import { Repository } from 'typeorm';

@Injectable()
export class PagamentoService {
  constructor(
    @InjectRepository(Pagamento)
    private pagamentoRepository: Repository<Pagamento>,
  ) {}

  create(createPagamentoDto: CreatePagamentoDto): Promise<Pagamento> {
    const pagamento: Pagamento = new Pagamento();
    pagamento.valor = createPagamentoDto.valor;
    pagamento.data = createPagamentoDto.data;
    pagamento.status = createPagamentoDto.status;
    pagamento.tipo = createPagamentoDto.tipo;
    pagamento.descricao = createPagamentoDto.descricao;

    return this.pagamentoRepository.save(pagamento);
  }

  findAll(): Promise<Pagamento[]> {
    return this.pagamentoRepository.find();
  }

  findOne(id: number): Promise<Pagamento> {
    return this.pagamentoRepository.findOneBy({ id });
  }

  update(
    id: number,
    updatePagamentoDto: UpdatePagamentoDto,
  ): Promise<Pagamento> {
    const pagamento: Pagamento = new Pagamento();

    pagamento.id = id;
    pagamento.valor = updatePagamentoDto.valor;
    pagamento.data = updatePagamentoDto.data;
    pagamento.status = updatePagamentoDto.status;
    pagamento.tipo = updatePagamentoDto.tipo;
    pagamento.descricao = updatePagamentoDto.descricao;

    return this.pagamentoRepository.save(pagamento);
  }

  remove(id: number): Promise<{ affected?: number }> {
    return this.pagamentoRepository.delete(id);
  }
}
