import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { ConfigModule } from '@nestjs/config';
import { TypeOrmModule } from '@nestjs/typeorm';
import { PagamentoModule } from './pagamento/pagamento.module';
import { Pagamento } from './pagamento/entities/pagamento.entity';
import { User } from './user/entities/user.entity';

@Module({
  imports: [
    TypeOrmModule.forFeature([User]),
    ConfigModule.forRoot({
      isGlobal: true,
      envFilePath: '.env',
    }),
    TypeOrmModule.forRoot({
      type: 'postgres',
      host: 'localhost',
      port: 5432,
      password: 'root',
      username: 'postgres',
      entities: [User, Pagamento],
      database: 'financial-management',
      synchronize: true,
      logging: true,
    }),
    PagamentoModule,
  ],
  controllers: [AppController],
  providers: [AppService],
})
export class AppModule {}
