class PlayersController < ApplicationController
  def index
    @players = Player.all
    
    if (params[:api_key] == "execute_order66")
      render json: @players
    else
      
    end

  end

  def show
    if (params[:api_key] == "execute_order66")
      @player = Player.find(params[:id])
      render json: @player
    else
      render json: {status: "Clave de autenticación erronea o vacia."}
    end
  end

  def login
    @player = Player.where(email: params[:email], password: params[:password]).first

    if @player.present?
      render json: {status: "Login correcto!"}
    else
      render json: {status: "Login fallido!"}
    end

  end

  def create
    if (params[:api_key] == "execute_order66")
      @player = Player.create(
        name: params[:name],
        email: params[:email],
        password: params[:password]
      )

      if @player.errors[:email].any?
        render json: {status: "Es probable que el correo no este en el correcto formato o ya este ocupado."}
      else
        render json: @player
      end

    else
      render json: {status: "Clave de autenticación erronea o vacia."}
    end
      
  end

  def update
    if (params[:api_key] == "execute_order66")
      @player = Player.find(params[:id])
      @player.update(
          name: params[:name],
          email: params[:email],
          password: params[:password]
      )
      render json: @player
    else
      render json: {status: "Clave de autenticación erronea o vacia."}
    end
  end

  def destroy
    if (params[:api_key] == "execute_order66")
      @players = Player.all
      @player = Player.find(params[:id])
      @player.destroy
      render json: @players
    else
      render json: {status: "Clave de autenticación erronea o vacia."}
    end
  end
end
