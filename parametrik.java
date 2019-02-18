// PARAMETRIK
	public void VariableType ()
	{
		// Menentukan apakah terdapat solusi dari matriks atau tidak
		// Menentukan tipe variabel yang teradapat dalam matriks dengan ketentuan :
		// 1 -> Variabel memiliki nilai pasti (Contoh : 2.00)
		// 2 -> Variabel memiliki nilai terikat variabel lain (Contoh : 2a - 3.00)
		// 3 -> Variabel memiliki nilai bebas (Contoh : a)

		// Inisiasi nilai pada class matrix
		vartype = new int[this.Kol - 1];
		isSolusi = true;

		// Set nilai awal variabel sebagai bebas
		for (int i = 0; i <= (this.Kol - 2); i++)
		{
			vartype[i] = 3;
		}

		// Atur nilai varibabel
		for (int i = (this.Brs - 1); i >= 0; i--)
		{
			// Cek matriks tidak memiliki solusi
			// Syarat : matriks tersebut memiliki IsBaris0(i) tapi nilai MatriksB
			// pada baris i tidak memiliki nilai 0
			if (this.IsBaris0(i) && (this.Mat[i][Kol - 1] != 0))
			{
				this.isSolusi = false;
				break;
			}
			else if (!this.IsBaris0(i))
			{
				int markj = Marker (i);
				// Cek apakah kanan kosong atau memiliki nilai terikat
				for (int j = (markj + 1); j <= (this.Kol - 2); j++)
				{
					if (this.Mat[i][j] != 0 && this.vartype[j] != 1)
					{
						this.vartype[markj] = 2;
						break;
					}
				}
				// Jika kanan kosong, maka elemen ini memiliki nilai pasti
				if (this.vartype[markj] == 3)
				{
					this.vartype[markj] = 1;
				}
			}
		}

		// Jika terdapat solusi
		if (isSolusi)
		{
			System.out.println("Terdapat solusi");
			/*for (int i = 0; i <= (Kol - 2); i++)
			{
				System.out.println("Vartype " + i + " = " + this.vartype[i]);
			}*/
		}
		else
		{
			System.out.println("Tidak terdapat solusi");
		}
	}

	public void Parametrik()
	{
		// PRASYARAT : Terdapat solusi dari matriks

		nilai = new double[this.Kol][this.Kol - 1];

		// INISIASI NILAI ARRAY
		for (int i = 0; i < this.Kol; i++)
		{
			for (int j = 0; j <= (this.Kol - 2); j++)
			{
				nilai[i][j] = 0;
			}
		}

		for (int i = (this.Brs - 1); i >= 0; i--)
		{
			if (!IsBaris0(i))
			{
				int markj = Marker(i);
				this.nilai[this.Kol - 1][markj] = this.Mat[i][this.Kol - 1];
				for (int j = markj + 1; j <= (this.Kol - 2); j++)
				{
					if (this.vartype[j] == 1)
					{
						this.nilai[this.Kol - 1][markj] -= this.nilai[this.Kol - 1][j] * this.Mat[i][j];
					}
					else if (this.vartype[j] == 2)
					{
						for (int k = (this.Kol - 1); k >= 0; k--)
						{
							this.nilai[k][markj] -= this.nilai[k][j] * this.Mat[i][j];
						}
					}
					else if (this.vartype[j] == 3)
					{
						this.nilai[j][markj] -= this.Mat[i][j];
					}
				}
			}
		}
	}

	public void ResulttoString()
	{
		solusi = new String [this.Kol - 1];

		// Menuliskan hasil dari pembacaan matriks nilai
		for (int j = 0; j <= (this.Kol - 2); j++)
		{
			if (vartype[j] == 1) // Nilai variabel pasti
			{
				this.solusi[j] = String.format("%.2f", this.nilai[this.Kol - 1][j]);
			}
			else if (vartype[j] == 2) // Nilai variabel terikat
			{
				String s = new String();
				boolean first = true;
				s = "";

				for (int i = 0; i <= (this.Kol - 2); i++)
				{

					if (!first && this.nilai[i][j] > 0)
					{
						s += "+";
					}

					if (this.nilai[i][j] != 0)
					{
						first = false;
						/* Memindahkan nilai indeks jadi alfabet
						 * Contoh : indeks 0 menjadi a*/
						s += String.format("%.2f", this.nilai[i][j]);
						char asconvert = (char)(i + 97);
						s += asconvert;
					}
				}
				if (this.nilai[this.Kol - 1][j] > 0 && !first)
				{
					s += "+";
				}
				s += String.format("%.2f", this.nilai[this.Kol - 1][j]);
				solusi[j] = s;
			}
			else if (vartype[j] == 3) // Nilai variabel bebas
			{
				/* Memindahkan nilai indeks jadi alfabet
				 * Contoh : indeks 0 menjadi a*/
				char asconvert = (char)(j + 97);
				this.solusi[j] = "" + asconvert;
			}
		}
	}
