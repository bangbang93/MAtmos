package eu.ha3.matmos.engine0.core.implem;

import eu.ha3.matmos.engine0.core.implem.abstractions.ProviderCollection;
import eu.ha3.matmos.engine0.core.interfaces.Provider;
import eu.ha3.matmos.engine0.core.interfaces.ReferenceTime;
import eu.ha3.matmos.engine0.core.interfaces.SheetCommander;
import eu.ha3.matmos.engine0.core.interfaces.SoundRelay;

/*
--filenotes-placeholder
*/

public class Providers implements ProviderCollection
{
	private final ReferenceTime time;
	private final SoundRelay soundRelay;
	private final SheetCommander commander;
	private final Provider<Condition> conditionProvider;
	private final Provider<Junction> junctionProvider;
	private final Provider<Machine> machineProvider;
	private final Provider<Event> eventProvider;
	
	public Providers(
		ReferenceTime time, SoundRelay soundRelay, SheetCommander commander, Provider<Condition> conditionProvider,
		Provider<Junction> junctionProvider, Provider<Machine> machineProvider, Provider<Event> eventProvider)
	{
		this.time = time;
		this.soundRelay = soundRelay;
		this.commander = commander;
		
		this.conditionProvider = conditionProvider;
		this.junctionProvider = junctionProvider;
		this.machineProvider = machineProvider;
		this.eventProvider = eventProvider;
	}
	
	@Override
	public ReferenceTime getReferenceTime()
	{
		return this.time;
	}
	
	@Override
	public SoundRelay getSoundRelay()
	{
		return this.soundRelay;
	}
	
	@Override
	public SheetCommander getSheetCommander()
	{
		return this.commander;
	}
	
	@Override
	public Provider getCondition()
	{
		return this.conditionProvider;
	}
	
	@Override
	public Provider getJunction()
	{
		return this.junctionProvider;
	}
	
	@Override
	public Provider getMachine()
	{
		return this.machineProvider;
	}
	
	@Override
	public Provider getEvent()
	{
		return this.eventProvider;
	}
	
}
