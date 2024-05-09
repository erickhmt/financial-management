import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { User } from './user/entities/user.entity';
import { Repository } from 'typeorm';

@Injectable()
export class AppService {
  constructor(
    @InjectRepository(User)
    private userRepository: Repository<User>,
  ) {}

  getHello(): string {
    return 'Hello World!';
  }

  async login({
    email,
    name,
    image,
  }: {
    email: string;
    name: string;
    image: string;
  }): Promise<any> {
    let user = await this.userRepository.findOneBy({ email });
    if (!user) {
      user = new User();
      user.email = email;
      user.name = name;
      user.image = image;

      await this.userRepository.save(user);
      console.log('Usuário criado: ', user);
      return user;
    } else {
      console.log('Usuário já existe: ', user);
      return user;
    }
  }
}
