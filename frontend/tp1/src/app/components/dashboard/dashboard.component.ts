import { Component, OnInit, ViewChild, ChangeDetectorRef } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { ChartType, ChartConfiguration } from 'chart.js';
import { NgChartsModule, BaseChartDirective } from 'ng2-charts';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, NgChartsModule],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  @ViewChild('pieChart') pieChart!: BaseChartDirective;
  @ViewChild('barChart') barChart!: BaseChartDirective;
  @ViewChild('campagneChart') campagneChart!: BaseChartDirective;

  public stats: any = {};
  public chartsReady = false;

  // 📊 Répartition statuts
  public pieChartData: ChartConfiguration<'pie'>['data'] = {
    labels: ['Envoyés', 'Échecs', 'En cours', 'Programmés'],
    datasets: [{ data: [0, 0, 0, 0], backgroundColor: ['#28a745', '#dc3545', '#17a2b8', '#ffc107'] }]
  };
  public pieChartType: ChartType = 'pie';

  // 📈 Emails par jour
  public barChartData: ChartConfiguration<'bar'>['data'] = {
    labels: [],
    datasets: [{ label: 'Emails envoyés par jour', data: [], backgroundColor: '#007bff' }]
  };
  public barChartType: ChartType = 'bar';

  // 🎯 Performance par campagne
  public campagneChartData: ChartConfiguration<'bar'>['data'] = {
    labels: [],
    datasets: [{ label: 'Emails par campagne', data: [], backgroundColor: '#6f42c1' }]
  };
  public campagneChartType: ChartType = 'bar';

  public chartOptions: ChartConfiguration['options'] = {
    responsive: true,
    maintainAspectRatio: false,
    plugins: { legend: { display: true } }
  };

  constructor(private http: HttpClient, private cdr: ChangeDetectorRef) {}

  ngOnInit(): void {
    this.loadDashboardData();
    // 🔄 refresh toutes les 30s pour simuler temps réel
    setInterval(() => this.loadDashboardData(), 30000);
  }

  private loadDashboardData(): void {
    this.http.get<any>('http://localhost:9090/dashboard/stats').subscribe({
      next: (stats) => {
        this.stats = stats;

        // Pie chart
        this.pieChartData.datasets[0].data = [
          stats.envoyes || 0,
          stats.echecs || 0,
          stats.enCours || 0,
          stats.programmes || 0
        ];

        // Bar chart (par jour)
        this.barChartData.labels = Object.keys(stats.mailsParJour || {});
        this.barChartData.datasets[0].data = Object.values(stats.mailsParJour || {});

        // Chart par campagne
        this.campagneChartData.labels = Object.keys(stats.mailsParCampagne || {});
        this.campagneChartData.datasets[0].data = Object.values(stats.mailsParCampagne || {});

        this.chartsReady = true;
        this.refreshCharts();
        this.cdr.detectChanges();
      },
      error: (error) => console.error('Erreur chargement stats:', error)
    });
  }

  private refreshCharts(): void {
    this.pieChart?.chart?.update();
    this.barChart?.chart?.update();
    this.campagneChart?.chart?.update();
  }
}
