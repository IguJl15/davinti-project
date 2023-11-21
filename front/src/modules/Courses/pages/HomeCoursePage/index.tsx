import { Body } from '../../../../core/components/Body';
import { Button } from './components/Button';
import { ContentCard } from './components/ContentCard';
import { DescriptionCard } from './components/DescripitionCard';
import { UserCard } from './components/UserCard';
import './style.css';

function HomeCoursePage() {
  return (
    <Body>
      <div className="mainWrapper">
        <div className="section_1">
          <div className="sectionTitle">
            <div className="title">
              <h2>CURSO DE VENDER CELTA</h2>
              <Button />
            </div>
            <p>
              Spener agon respektive gall fulfillment. Neodiktisk nende med önyrad tret facial
              recognition lebel såväl som teniledes, laras. Neodiktisk nende med önyrad tret facial
              recognition lebel såväl som teniledes lebel såväl som
            </p>
          </div>
        </div>
        <div className="section_2">
          <div className="description">
            <DescriptionCard />
            <UserCard
              name="Igor Julliano"
              job="Analise e Desenvolvimento"
              role="Instituto Federal do Piaui IFPI"
            />
          </div>
          <div className="content">
            <ContentCard />
          </div>
        </div>
      </div>
    </Body>
  );
}

export { HomeCoursePage };
