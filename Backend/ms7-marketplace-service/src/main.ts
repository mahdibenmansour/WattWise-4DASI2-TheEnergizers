import 'reflect-metadata';
import { NestFactory } from '@nestjs/core';
import { DocumentBuilder, SwaggerModule } from '@nestjs/swagger';
import { AppModule } from './app.module';

async function bootstrap(): Promise<void> {
  const app = await NestFactory.create(AppModule);
  app.setGlobalPrefix('api/v1');
  app.enableShutdownHooks();
  const config = new DocumentBuilder()
    .setTitle('MS7 Energy Marketplace API')
    .setDescription('Standalone Marketplace starter API')
    .setVersion('1.0')
    .build();
  const document = SwaggerModule.createDocument(app, config);
  SwaggerModule.setup('docs', app, document);
  await app.listen(8087);
  console.log('Marketplace API: http://localhost:8087/api/v1/hello');
  console.log('Swagger UI: http://localhost:8087/docs');
}

bootstrap().catch((error: unknown) => {
  console.error(error);
  process.exitCode = 1;
});
